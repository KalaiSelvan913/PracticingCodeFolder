import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/compat/firestore';
import { ToastrService } from 'ngx-toastr';
import { Category } from '../models/category';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor(private afs: AngularFirestore, private toastr: ToastrService) { }

  saveData(data : Category) {
    this.afs
      .collection('categories')
      .add(data)
      .then((docRef) => {
        console.log(docRef);
        this.toastr.success('Data Insert Successfully ..!')
      })
      .catch((err) => {
        console.log(err);
      });
  }

  loadData() {

    this.afs.collection('categories').snapshotChanges().pipe(
      map(actions  => {
        actions.map(a => {
          const data = a.payload.doc.data();
          const id = a.payload.doc.id;
        })
      })
    )

  }

}
