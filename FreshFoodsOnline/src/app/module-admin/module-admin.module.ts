import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminHeaderComponent } from './admin-header/admin-header.component';
import { ModuleAdminRoutingModule } from './module-admin-routing.module';
import { AdminComponent } from './admin/admin.component';
import { AboutComponent } from './about/about.component';
import { FoodListComponent } from './food-list/food-list.component';
import { FoodComponent } from './food/food.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminUserComponent } from './admin-user/admin-user.component';
import { NewCategoryComponent } from './new-category/new-category.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { EditCategoryComponent } from './edit-category/edit-category.component';
import { CommandListComponent } from './command-list/command-list.component';



@NgModule({
  declarations: [
    AdminComponent,
    AdminHeaderComponent,
    AboutComponent,
    FoodListComponent,
    FoodComponent,
    AdminUserComponent,
    NewCategoryComponent,
    CategoryListComponent,
    EditCategoryComponent,
    CommandListComponent,
  ],
  imports: [
    CommonModule,
    ModuleAdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [
    ReactiveFormsModule,
    FormsModule,
    AdminHeaderComponent
  ],
})
export class ModuleAdminModule { }
