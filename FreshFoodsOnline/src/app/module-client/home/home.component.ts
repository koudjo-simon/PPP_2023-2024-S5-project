import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from '../../services/cart.service';
import {Product} from "../../shared/model/Product";
import {ProductService} from "../../module-admin/admin-services/product.service";
import {Food} from "../../shared/model/food";
import {FoodService} from "../../services/food.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  products: Food[] = [];
  private _foodFilter: string = '';
  public filteredProduct: Food[] = [];
  private selectedProduct: Food[] = [];

  constructor(private prodService: ProductService,
    private route: ActivatedRoute,
    private foodService: FoodService,
    private cartService: CartService,
    private router: Router){ }

  ngOnInit(): void{
    this.route.params.subscribe(params => {

    })
    this.foodService.getAll().subscribe({
      next: data => {
        this.products = data;
        console.log(this.products)
        this.filteredProduct = this.products;
      },
      error: err => console.log(err)
    });
  }

  public get foodFilter(): string {
    return this._foodFilter;
  }

  public set foodFilter(filter: string){
    this._foodFilter = filter;
    this.filteredProduct = this.foodFilter ? this.filterFoods(this.foodFilter) : this.products;
  }

  private filterFoods(criteria: string): Food[] {
    criteria = criteria.toLowerCase();
    const result = this.products.filter(
      (food: Food) => food.name.toLowerCase().indexOf(criteria) != -1
    );
    return result;
  }

  public addToPanier(prod: Food): void{
    this.selectedProduct.push(prod);
    this.cartService.addToCart(prod);
  }

  public goToFoodDetail(id: number){
    this.router.navigateByUrl("client/food/" + id).then(r  => true);
  }



}
