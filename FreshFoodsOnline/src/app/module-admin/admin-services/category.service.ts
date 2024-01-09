import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Category} from "../../shared/model/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getCategories(){
    return this.http.get<Category[]>(this.apiUrl + "/category/list");
  }

  getCategoryById(categoryId: number){
    return this.http.get<Category>(this.apiUrl + "/category/"+categoryId)
  }

  addCategory(name: string, description: string){
    let category = {
      name,
      description
    }
    return this.http.post<Category>(this.apiUrl + "/category/add", category)
  }

  updateCategory(categoryId: number, name: string, description: string){
    let category = {
      name,
      description
    }
    return this.http.put<Category>(this.apiUrl + "/category/"+categoryId+"/update", category)
  }

  deleteCategoryById(categoryId: number){
    return this.http.delete(this.apiUrl + "/category/"+categoryId+"/delete")
  }

}
