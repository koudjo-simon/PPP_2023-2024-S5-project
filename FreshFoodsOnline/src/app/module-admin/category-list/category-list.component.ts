import { Component } from '@angular/core';
import {Admin} from "../admin-model/admin";
import {Product} from "../../shared/model/Product";
import {FoodService} from "../../services/food.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../admin-services/authentication/authentication.service";
import {CategoryService} from "../admin-services/category.service";
import {Category} from "../../shared/model/category";

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent {

  public authAdmin!: Admin;
  public categories: Category[] = [];
  public errorMessage!: string;
  private _searchKeyword: string = "";
  public searchCategories: Category[] = [];

  constructor(private foodService: FoodService,
              private router: Router,
              private catService: CategoryService,
              private authService: AuthenticationService){}

  ngOnInit(): void {

    this.catService.getCategories().subscribe({
      next: (prd) => {
        this.categories = prd;
        this.searchCategories = this.categories;
        console.log(this.categories);
      },
      error: err => {
        console.error(err);
        this.errorMessage = err;
      }
    });
  }

  public get searchKeyword(): string{
    return this._searchKeyword;
  }

  public set searchKeyword(keyword: string){
    this._searchKeyword = keyword;
    this.searchCategories = this.searchKeyword ? this.filterFoods(this.searchKeyword) : this.categories;
  }

  private filterFoods(criteria: string): Category[]{
    criteria = criteria.toLowerCase();
    const res = this.categories.filter(
      (cat: Category) => cat.name.toLowerCase().indexOf(criteria) != -1
    );
    return res;
  }

  handleDeleteCategory(cat: Category){
    let conf = confirm("Are you sure that you want to delete food "+cat.name+" ?");
    if (!conf) return;
    this.catService.deleteCategoryById(cat.categoryId).subscribe({
      next : data => {
        let index = this.categories.indexOf(cat);
        this.categories.splice(index, 1);
      }
    });
  }

  handleEditCategory(cat: Category){
      let url = "/admin/category/"+ "edit/" + cat.categoryId;
      this.router.navigateByUrl(url);
  }

}
