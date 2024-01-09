import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Product} from "../../shared/model/Product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  addImage(formData: FormData): Observable<any>{
    return  this.http.post(this.apiUrl + "/uploadFile", formData);
  }

  addProduct(categoryId: number, name: string, price: number, description: string, imageUrl: string, stockQte: number, productStatus: string){
    let product = {
      name,
      price,
      description,
      imageUrl,
      stockQte,
      productStatus
    }
    return this.http.post(this.apiUrl + "/product/add?categoryId="+categoryId, product);
  }

  getPoducts(){
    return this.http.get<Product[]>(this.apiUrl + "/product/list");
  }

  getProductById(productid: number){
    return this.http.get<Product>(this.apiUrl + "/product/"+productid);
  }

  updateProduct(productId: number, categoryId: number, name: string, price: number, description: string, imageUrl: string, stockQte: number, productStatus: string){
    let product = {
      name,
      price,
      description,
      imageUrl,
      stockQte,
      productStatus,
      category: categoryId,
    }
    console.log(product)
    return this.http.put<Product>(this.apiUrl + "/product/"+productId+"/update", product);
  }

  deleteProductById(productId: number){
    return this.http.delete(this.apiUrl + "/product/"+productId)
  }

}
