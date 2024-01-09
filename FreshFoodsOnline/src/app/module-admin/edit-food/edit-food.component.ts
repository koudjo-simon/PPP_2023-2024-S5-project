import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ProductService} from "../admin-services/product.service";
import {ActivatedRoute} from "@angular/router";
import {MyValidatorsService} from "../../services/my-validators.service";
import {Category} from "../../shared/model/category";
import {CategoryService} from "../admin-services/category.service";

@Component({
  selector: 'app-edit-food',
  templateUrl: './edit-food.component.html',
  styleUrls: ['./edit-food.component.scss']
})
export class EditFoodComponent implements OnInit{

  // TODO: à réaliser plus tard

  public productFormGroup!: FormGroup;
  private productId!: number;
  selectedFile! : File;
  categoryList: Category[] = [];
  productStatus: any[] = [];

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              public myValService: MyValidatorsService,
              private productService: ProductService,
              private catService: CategoryService) { }

  ngOnInit(): void {
    this.productStatus = [
      {name: "CREATED", value: "CREATED"},
      {name: "AVAILABLE", value: "AVAILABLE"},
      {name: "NOT AVAILABLE", value: "NOT_AVAILABLE"}
    ]
    this.route.paramMap.subscribe(params => {
        this.productId = Number(params.get("id"));
    })
    this.productFormGroup = this.fb.group({
      name: ['', Validators.required],
      category: ['', Validators.required],
      price: ['', Validators.required, Validators.min(0)],
      description: ['', Validators.required],
      image: ['', Validators.required],
      quantity: ['', Validators.required],
      productStatus: ['', Validators.required],
    });

    this.catService.getCategories().subscribe({
      next: cats => {
        this.categoryList = cats;
      }
    })

    this.productService.getProductById(this.productId).subscribe({
      next: p => {
        console.log("Product: ", p);
        this.productFormGroup.patchValue({name: p.name});
        this.productFormGroup.patchValue({category: p.category.categoryId});
        this.productFormGroup.patchValue({price: p.price});
        this.productFormGroup.patchValue({description: p.description});
        this.productFormGroup.patchValue({image: p.imageUrl});
        this.productFormGroup.patchValue({quantity: p.stockQte});
        this.productFormGroup.patchValue({productStatus: p.productStatus});
      }
    })
  }

  onFileSelected(event: any){
    this.selectedFile = event.target.files[0];
  }

  onProductStatusChange(event: any): void {
    const selectedValue = event.target.value;
    this.productFormGroup.get('productStatus')?.setValue(selectedValue);
  }

  handleSubmit() {
    let name = this.productFormGroup.value.name;
    let categoryId = this.productFormGroup.value.category;
    let price = this.productFormGroup.value.price;
    let description = this.productFormGroup.value.description;
    let quantity = this.productFormGroup.value.quantity;
    let productStatus = this.productFormGroup.get('productStatus')?.value;
    let imageUrl = null;

    console.log("Product status: ", productStatus)

    // Ajout de l'image
    const formData = new FormData();
    formData.append('file', this.selectedFile);
    this.productService.addImage(formData).subscribe({
      next: (value: any) => {
        imageUrl = value.fileDownloadUri;
        this.productService.updateProduct(this.productId, categoryId, name, price, description, imageUrl, quantity, productStatus)
          .subscribe({
            next: prod => {
              alert("Product successfully update!");
            },
            error: err => {
              alert("Error: Product not add correctly");
              console.log(err)
            }
          })
      }
    })


  }
}
