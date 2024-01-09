import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MyValidatorsService} from "../../services/my-validators.service";
import {CategoryService} from "../admin-services/category.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.scss']
})
export class EditCategoryComponent {

  categoryFormGroup !: FormGroup;
  categoryId !: number

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              public myValService: MyValidatorsService,
              private catService: CategoryService) { }

  ngOnInit() {

    this.categoryFormGroup = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
    })

    this.route.paramMap.subscribe(params => {
      this.categoryId = Number(params.get('id'));
    })

    this.catService.getCategoryById(this.categoryId).subscribe({
      next: cat => {
        console.log(cat);
        this.categoryFormGroup.patchValue({name: cat.name});
        this.categoryFormGroup.patchValue({description: cat.description});
      }
    })
  }

  handleSubmit() {
    let name = this.categoryFormGroup.value.name;
    let description = this.categoryFormGroup.value.description;

    this.catService.updateCategory(this.categoryId, name, description).subscribe({
      next : value => {
        alert("Category successfully update !")
      },
      error: err => {
        console.log(err)
      }
    })
  }

}
