import {Category} from "./category";

export interface Product{
  productId : number;
  name: string;
  category: Category;
  price: number;
  description: string;
  imageUrl: string;
  stockQte: number;
  addDate: Date;
  productStatus: string;
}
