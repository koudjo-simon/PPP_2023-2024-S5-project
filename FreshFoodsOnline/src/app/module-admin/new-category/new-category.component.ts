import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MyValidatorsService} from "../../services/my-validators.service";
import {CategoryService} from "../admin-services/category.service";

@Component({
  selector: 'app-new-category',
  templateUrl: './new-category.component.html',
  styleUrls: ['./new-category.component.scss']
})
export class NewCategoryComponent implements OnInit{

  categoryFormGroup !: FormGroup;

  constructor(private fb: FormBuilder,
              public myValService: MyValidatorsService,
              private catService: CategoryService) { }

  ngOnInit() {

    this.categoryFormGroup = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
    })

  }

  handleSubmit() {
    let name = this.categoryFormGroup.value.name;
    let description = this.categoryFormGroup.value.description;

    this.catService.addCategory(name, description).subscribe({
      next : value => {
        alert("Category successfully registered !")
      },
      error: err => {
        console.log(err)
      }
    })
  }
}
