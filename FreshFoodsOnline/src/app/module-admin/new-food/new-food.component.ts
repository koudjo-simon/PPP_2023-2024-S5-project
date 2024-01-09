import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MyValidatorsService } from '../../services/my-validators.service';
import {AdminFoodService} from "../admin-shared/services/admin-food.service";
import {ProductService} from "../admin-services/product.service";
import {Category} from "../../shared/model/category";
import {CategoryService} from "../admin-services/category.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-food',
  templateUrl: './new-food.component.html',
  styleUrls: ['./new-food.component.scss']
})
export class NewFoodComponent implements OnInit {

  public foodFormGroup! : FormGroup;
  selectedFile! : File;
  categoryList: Category[] = [];

  constructor(private fb: FormBuilder,
              private router: Router,
              public myValService: MyValidatorsService,
              private productService: ProductService,
              private catService: CategoryService){}

  ngOnInit(): void {
      this.catService.getCategories().subscribe({
        next: cats => {
          this.categoryList = cats;
        },
        error: err => {
          console.log(err);
        }
      })
      this.foodFormGroup = this.fb.group({
        name: ['', Validators.required],
        category: ['', Validators.required],
        price: ['', Validators.required, Validators.min(0)],
        description: ['', Validators.required],
        image: ['', Validators.required],
        quantity: ['0', Validators.required],
        productStatus: ['False', Validators.required],
      })
  }

  onFileSelected(event: any){
    this.selectedFile = event.target.files[0];
  }

  handleSubmit(){
    let name = this.foodFormGroup.value.name;
    let categoryId = this.foodFormGroup.value.category;
    let price = this.foodFormGroup.value.price;
    let description = this.foodFormGroup.value.description;
    let quantity = this.foodFormGroup.value.quantity;
    let productStatus = this.foodFormGroup.value.productStatus;
    let imageUrl = null;

    // Ajout de l'image
    const formData = new FormData();
    formData.append('file', this.selectedFile);
    this.productService.addImage(formData).subscribe({
      next: (value: any) => {
        imageUrl = value.fileDownloadUri;
        this.productService.addProduct(categoryId, name, price, description, imageUrl, quantity, productStatus)
          .subscribe({
            next: prod => {
              alert("Product successfully add!");
              this.router.navigateByUrl("/admin/product/all");
            },
            error: err => {
              alert("Error: Product not add correctly");
              console.log(err);
            }
          })
      }
    })

  }

}
