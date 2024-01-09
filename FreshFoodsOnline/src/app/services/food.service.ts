import {Injectable} from '@angular/core';
import {Observable, of, throwError} from 'rxjs';
import {Food} from 'src/app/shared/model/food';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  private foods: Food[] = [
    {
      foodId: 1,
      name: "belt",
      price: 5,
      favorite: true,
      star: 3.5,
      tags: ["Ceinture"],
      imageUrl: "./assets/images/belt.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 2,
      name: "clothes x",
      price: 5,
      favorite: false,
      star: 3.5,
      tags: ["FastFoo", ],
      imageUrl: "./assets/images/clothes-1.jpg",
      cookTime: "10-45",
      origins: ["Germany", "US"],
      lastModifiedDate: "",
      foodStatus: "",
      addDate: ""
    },
    {
      foodId: 3,
      name: "WOMAN FINITY",
      price: 20,
      favorite: true,
      star: 4.7,
      tags: ["Slow", "woman"],
      imageUrl: "./assets/images/clothes-2.jpg",
      cookTime: "20-30",
      origins: ["Persia", "Middle east", "China"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 4,
      name: "jacket maron",
      price: 12,
      favorite: true,
      star: 4.5,
      tags: ["jacket", "maron"],
      imageUrl: "./assets/images/jacket-1.jpg",
      cookTime: "10-15",
      origins: ["Germany", "US"],
      lastModifiedDate: "",
      foodStatus: "",
      addDate: ""
    },
    {
      foodId: 5,
      name: "jacket gris",
      price: 20,
      favorite: false,
      star: 4.0,
      tags: ["jacket", "Pizza"],
      imageUrl: "./assets/images/jacket-2.jpg",
      cookTime: "40-50",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 6,
      name: "jacket vert",
      price: 20,
      favorite: true,
      star: 4.0,
      tags: ["jacket", "vert"],
      imageUrl: "./assets/images/jacket-3.jpg",
      cookTime: "40-50",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 7,
      name: "jacket bleue",
      price: 10,
      favorite: false,
      star: 4.5,
      tags: ["jacket", "bleue", "Lunch"],
      imageUrl: "./assets/images/jacket-4.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 8,
      name: "veste cafe",
      price: 10,
      favorite: false,
      star: 4.5,
      tags: ["cafe", "veste", "Lunch"],
      imageUrl: "./assets/images/jacket-5.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 9,
      name: "jacket flex",
      price: 10,
      favorite: false,
      star: 4.5,
      tags: ["Flex", "jacket", "Lunch"],
      imageUrl: "./assets/images/jacket-6.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 10,
      name: "party wear",
      price: 10,
      favorite: false,
      star: 4.5,
      tags: ["party-wear-1", "chaussure", "Lunch"],
      imageUrl: "./assets/images/party-wear-1.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 11,
      name: "shirt maoe blue",
      price: 10,
      favorite: false,
      star: 4.5,
      tags: ["shirt", "maoe", "Lunch"],
      imageUrl: "./assets/images/shirt-1.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 12,
      name: "shirt maoe blanc",
      price: 10,
      favorite: false,
      star: 4.5,
      tags: ["shirt", "maoe", "Lunch"],
      imageUrl: "./assets/images/shirt-2.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 13,
      name: "shoes black",
      price: 10,
      favorite: false,
      star: 4.5,
      tags: ["shoes", "chaussure", "Lunch"],
      imageUrl: "./assets/images/shoe-1_1.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 14,
      name: "Cuire cafe",
      price: 10,
      favorite: false,
      star: 4.5,
      tags: ["chaussure", "cuire", "Lunch"],
      imageUrl: "./assets/images/shoe-2.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 15,
      name: "short orange",
      price: 10,
      favorite: false,
      star: 4.5,
      tags: ["habit", "short", "Lunch"],
      imageUrl: "./assets/images/shorts-1.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },
    {
      foodId: 16,
      name: "new balance",
      price: 10,
      favorite: false,
      star: 4.5,
      tags: ["new balance", "chaussure", "Lunch"],
      imageUrl: "./assets/images/sports-3.jpg",
      cookTime: "10-12",
      origins: ["Italy"],
      addDate: "",
      foodStatus: "",
      lastModifiedDate: ""
    },

  ];

  constructor() {

  }

  public getAll(): Observable<Food[]> {
    return of(this.foods);
  }

  public getFoodById(id: number): Observable<Food> {
    let fd = this.foods.find(f => f.foodId == id);
    if (fd == undefined) return throwError(() => new Error("Foods undefined"));
    return of(fd);
  }

  public deleteProductById(id: number): Observable<boolean> {
    this.foods = this.foods.filter(p => p.foodId != id);
    return of(true);
  }

  public editFoodById(food: Food): Observable<Food> {
    this.foods = this.foods.map(f => (f.foodId == food.foodId) ? food : f);
    return of(food);
  }

}
