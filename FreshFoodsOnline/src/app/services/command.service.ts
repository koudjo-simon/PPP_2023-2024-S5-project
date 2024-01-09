import {Injectable} from '@angular/core';
import {Command} from '../shared/model/command';
import {CommandLine} from '../shared/model/command-line';
import {Observable, of, throwError} from 'rxjs';
import {HttpClient} from "@angular/common/http";
import {Customer} from "../shared/model/customer";
import {CartService} from "./cart.service";

@Injectable({
  providedIn: 'root'
})
export class CommandService {

  private commandList: Command[] = [];
  private commandLineList: CommandLine[] = [];
  public commandPassed: boolean = false;

  apiUrl = "http://localhost:8080/api/"

  constructor(private http: HttpClient, private cartService: CartService) {
    cartService.getCartCmdLinesFoods().subscribe({
      next: value => {
        this.commandLineList = value;
        console.log("Getting command lines of the cart");
      }
    })
  }

  public addCommand(cmd: Command): Observable<any> {
    this.commandList.push(cmd);
    return of(true)
    /*if (cmd == undefined) return throwError(() => new Error("Command add failed"));
    /!*const cust: Customer = {
      customerId: "",
      firstname: cmd.customer.firstname,
      age: cmd.customer.age,

    }*!/
    this.http.post<Customer>(this.apiUrl + "customers/add", cmd.customer).subscribe({
      next: customer => {
        console.log("My customerId: " + customer.customerId)
        this.http.get<Command>(this.apiUrl + "commands/addFor/" + customer.customerId)
          .subscribe({
          next: command => {
            console.log("Affichage de la list des command Lines: ", this.commandLineList)
            this.commandLineList.forEach(commandLine => {
              this.http.get<CommandLine>(this.apiUrl + "commandLine/add?foodId="
                + "1d799841-c54e-4c72-8e2a-842e5214fe1c" + "&&commandId=" + command.commandId +
                "&&quantity=" + commandLine.quantity).subscribe({
                next: cmdLine => {
                  console.log("The command passed successfully");
                },
                error: err => throwError(() => new Error(err))
              })
            })
          }
        });
      },
      error: err => throwError(() => new Error(err))
    });
    return of(cmd)*/
  }

  public setCommandLines(cmdLines: CommandLine[]) {
    if (cmdLines.length > 0) {
      cmdLines.forEach(cmdLine => {
        this.commandLineList.push(cmdLine);
      })
    }
  }

  public getCommands(): Observable<Command[]> {
    if (this.commandList.length == 0)
      return throwError(() => new Error("Command List is empty"));
    return of(this.commandList);
  }

  public getCommandLines(): Observable<CommandLine[]> {
    if (this.commandLineList.length == 0)
      return throwError(() => new Error("Command Line list is empty."));
    return of(this.commandLineList);
  }

  private setCommandPassed() {
    this.commandPassed = true;
  }

  public isCommandPassed(): boolean {
    return this.commandPassed
  }

}
