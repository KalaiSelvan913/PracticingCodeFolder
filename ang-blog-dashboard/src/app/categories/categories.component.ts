import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit  {

 constructor() {}

  ngOnInit(): void {
  }

  onSubmit(formData : any) : void {
    console.log(formData.value)
  }

}
