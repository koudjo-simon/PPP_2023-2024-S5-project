import {Injectable} from '@angular/core';
import {Observable, of, throwError} from 'rxjs';
import {CommandLine} from "../shared/model/command-line";
import {Product} from "../shared/model/Product";
import {Food} from "../shared/model/food";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cardFoodsList: Food[] = [];
  private cardCommandLinesList: CommandLine[] = [];

  constructor() { }

  public addToCart(prod: Food): void {
    this.cardFoodsList.push(prod);
    /*this.cardFoodsList.forEach(f => {
      this.cardCommandLinesList.forEach(c => {
        const existingCommandLine = this.cardCommandLinesList
          .findIndex(commandLine => commandLine.food.foodId == food.foodId);
        if (existingCommandLine) {
          this.cardCommandLinesList[existingCommandLine].quantity++;
          this.cardCommandLinesList[existingCommandLine].totalCommandLinePrice += food.price;
        } else {
          let commandLine: CommandLine = {
            commandLineId: "",
            food: f,
            commandId: "",
            unitPrice: f.price,
            quantity: 1,
            totalCommandLinePrice: f.price
          };
          this.cardCommandLinesList.push(commandLine);
        }
      });
      })*/
    // Recherche d'une ligne de commande comportant deja cette Food
    const existingCommandLineIndex = this.cardCommandLinesList
      .findIndex(cmdLine => cmdLine.product.foodId === prod.foodId);
    if (existingCommandLineIndex != -1){
      this.cardCommandLinesList[existingCommandLineIndex].quantity++;
      this.cardCommandLinesList[existingCommandLineIndex].totalCommandLinePrice += prod.price;
    }else {
      let commandLine: CommandLine = {
        commandLineId: "",
        product: prod,
        commandId: "",
        unitPrice: prod.price,
        quantity: 1,
        totalCommandLinePrice: prod.price
      };
      this.cardCommandLinesList.push(commandLine);
    }
  }

  public addCommandLinesToTheCart(cmdLines: Array<CommandLine>): Observable<boolean> {
    cmdLines.forEach(cmdLine => {
      this.cardCommandLinesList.push(cmdLine)
    });
    return of(true);
  }

  public getCartCmdLinesFoods(): Observable<CommandLine[]> {
    if (this.cardFoodsList.length > 0) {
      return of(this.cardCommandLinesList);
    } else {
      return throwError(() => new Error("Food untifined in the cart list."));
    }
  }

  public getTotalPrice(): Observable<number> {
    let price: number = 0;
    this.cardCommandLinesList.forEach(cmdLine => {
      price += cmdLine.totalCommandLinePrice;
    });
    return of(price);
  }

  public deleteComandeLine(commandLineId: string): Observable<boolean> {
    this.cardCommandLinesList = this.cardCommandLinesList.filter(c => c.commandLineId == commandLineId);
    return of(true);
  }

  public deleteFoodById(id: number): Observable<boolean> {
    this.cardFoodsList = this.cardFoodsList.filter(prod => prod.foodId == id);
    return of(true);
  }

  public cardContainAddFoods() {
    return this.cardFoodsList.length > 0;
  }

}
