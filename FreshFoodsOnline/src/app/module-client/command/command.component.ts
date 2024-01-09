import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';
import {Food} from 'src/app/shared/model/food';
import { Command } from '../../shared/model/command';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from 'src/app/shared/model/customer';
import { CommandLine } from 'src/app/shared/model/command-line';
import { CommandService } from '../../services/command.service';
import { Router } from '@angular/router';
import { MyValidatorsService } from 'src/app/services/my-validators.service';

@Component({
  selector: 'app-command',
  templateUrl: './command.component.html',
  styleUrls: ['./command.component.scss']
})
export class CommandComponent implements OnInit{

  public commandFoods!: Food[];
  public errorMessage!: string;
  public totalPrice: number = 0;

  public commandLines: CommandLine[] = [];

  public commandFormGroup!: FormGroup;

  constructor(private cartService: CartService,
    private fb: FormBuilder,
    private cmdService: CommandService,
    private router: Router,
    public myValService: MyValidatorsService){
  }

  ngOnInit(): void {
    this.cartService.getCartCmdLinesFoods().subscribe({
      next: data => {
        this.commandLines = data;
        this.commandLines.forEach(cl => {
          this.totalPrice = this.totalPrice + cl.totalCommandLinePrice;
        })
      },
      error: err => {
        console.log(err);
      }
    })

    this.commandFormGroup = this.fb.group({
      firstname: this.fb.control('', Validators.required),
      lastname: ['', [Validators.required]],
      bithdate: ['', [Validators.required]],
      email: ['', [Validators.email, Validators.required]],
      country: ['', [Validators.required]],
      region: ['', [Validators.required]],
      address: ['', [Validators.required]]
    });
  }

  public handleCommandClicked(){
    let firstname = this.commandFormGroup.value.firstname;
    let lastname = this.commandFormGroup.value.lastname;
    let bithdate = this.commandFormGroup.value.bithdate;
    let email = this.commandFormGroup.value.email;
    let country = this.commandFormGroup.value.country;
    let region = this.commandFormGroup.value.region;
    let address = this.commandFormGroup.value.address;

    let customer: Customer = {
      customerId: "",
      firstname: firstname,
      lastname: lastname,
      age: 10,
      email: email,
      country: country,
      region: region,
      address: address
    }

    /* this.commandFoods.forEach(element => {
      let commandLine: CommandLine = {
        food: element,
        quantity: 1,
        totalPrice: element.price
      }
      this.commandLines.push(commandLine);
    }); */

    let command: Command = {
      commandId: "",
      totalCommandPrice: 0,
      customer: customer,
      commandDate: "",
      lastModifiedDate: "",
      commandStatus: ""
    }

    this.cmdService.addCommand(command).subscribe({
      next: data => {
        console.log(data);
        this.router.navigateByUrl("/client/command_success");
      }
    });
  }

  public setCommandPassed(){

  }

}
