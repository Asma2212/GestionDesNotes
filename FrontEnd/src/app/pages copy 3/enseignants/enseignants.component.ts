import { Component } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { SmartTableData } from '../../@core/data/smart-table';
import { EnseignantService } from '../../services/enseignant.service';
import { ToastrComponent } from '../modal-overlays/toastr/toastr.component';
import { data } from 'jquery';
import { Enseignant } from '../../models/Enseignant';
import { EtudiantService } from '../../services/etudiant.service';
import { Etudiant } from '../../models/Etudiant';

@Component({
  selector: 'ngx-enseignants',
  templateUrl: './enseignants.component.html',
  styleUrls: ['./enseignants.component.scss']
})
export class EnseignantsComponent {
  etudiants : Etudiant[] = [];

  source: LocalDataSource = new LocalDataSource();

  constructor(private service: SmartTableData,private etudiantService : EtudiantService) {
    this.etudiantService.getAllEtudiants().subscribe(data => {
      this.etudiants = data ;
      let etud : Etudiant ;
      /*etudiants.forEach(element => {
        this.etudiantService.getNotesOfEtudByMatiere(element.email).subscribe(data => {
          etud = data.
      });*/
    }, error => {
      if(error.status===400){  
           //this.toast.error({summary: 'Verifier vos informations"',detail: "Erreur",duration:3000});
      }

    }
    );
  }

  onDeleteConfirm(event): void {
    if (window.confirm('Etes vous sur vous voulez le supprimer?')) {
      event.confirm.resolve();
      this.etudiantService.deleteEtudiant(event.data.id).subscribe(data => {
          
      });
    }else{
      event.confirm.reject();
    }
  }
  onSaveConfirm(event):void{
    console.log("edit"+JSON.stringify(event.newData));
    this.etudiantService.updateEtudiant(event.newData).subscribe(data => {
      console.log("enseignant ajouter avec succée");
      
    }, error => {
      console.log("erreur save") ;
    });

  }
}
