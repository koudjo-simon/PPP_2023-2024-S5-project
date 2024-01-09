import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FoodService } from 'src/app/services/food.service';
import { Food } from 'src/app/shared/model/food';
import { Admin } from '../admin-model/admin';
import { AuthenticationService } from '../admin-services/authentication/authentication.service';
import {ProductService} from "../admin-services/product.service";
import {Product} from "../../shared/model/Product";

@Component({
  selector: 'app-food-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.scss']
})
export class FoodListComponent implements OnInit{

  public authAdmin!: Admin;
  public products: Product[] = [];
  public errorMessage!: string;
  private _searchKeyword: string = "";
  public searchProducts: Product[] = [];

  constructor(private foodService: FoodService,
    private router: Router,
    private productService: ProductService,
    private authService: AuthenticationService){}

  ngOnInit(): void {
    this.authService.getAuthenticatedUser().subscribe({
      next: user => this.authAdmin = user,
      error: err => console.log(err)
    });

    this.productService.getPoducts().subscribe({
      next: (prd) => {
        this.products = prd;
        this.searchProducts = this.products;
        console.log(this.products);
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
    this.searchProducts = this.searchKeyword ? this.filterFoods(this.searchKeyword) : this.products;
  }

  private filterFoods(criteria: string): Product[]{
    criteria = criteria.toLowerCase();
    const res = this.products.filter(
      (prod: Product) => prod.name.toLowerCase().indexOf(criteria) != -1
    );
    return res;
  }

  handleDeleteFood(product: Product){
    let conf = confirm("Are you sure that you want to delete food "+product.name+" ?");
    if (conf == false) return;
    this.productService.deleteProductById(product.productId).subscribe({
      next : data => {
        let index = this.products.indexOf(product);
        this.products.splice(index, 1);
      }
    });
  }

  handleEditFood(product: Product){
      let url = "/admin/product/edit/" + product.productId;
      this.router.navigateByUrl(url).then();
  }

}
