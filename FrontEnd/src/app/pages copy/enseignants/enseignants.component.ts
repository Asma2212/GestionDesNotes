import { Component } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { SmartTableData } from '../../@core/data/smart-table';
import { EnseignantService } from '../../services/enseignant.service';
import { ToastrComponent } from '../modal-overlays/toastr/toastr.component';
import { data } from 'jquery';
import { Enseignant } from '../../models/Enseignant';

@Component({
  selector: 'ngx-enseignants',
  templateUrl: './enseignants.component.html',
  styleUrls: ['./enseignants.component.scss']
})
export class EnseignantsComponent {
  ens : Enseignant ;
  settings = {
    add: {
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
      confirmCreate: true,
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
      confirmSave: true,
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    columns: {
      id: {
        title: 'ID',
        type: 'number',
      },
      nom: {
        title: 'nom',
        type: 'string',
      },
      prenom: {
        title: 'prenom',
        type: 'string',
      },
      genre: {
        title: 'genre',
        type: 'string',
      },
      tel: {
        title: 'Tel',
        type: 'number',
      },
      email: {
        title: 'E-mail',
        type: 'string',
      },
      position: {
        title: 'Statut',
        type: 'string',
      },
    },
  };

  source: LocalDataSource = new LocalDataSource();

  constructor(private service: SmartTableData,private enseignantService : EnseignantService) {
    this.enseignantService.getAllEnseignants().subscribe(data => {
      const enseignants = data ;
      this.source.load(enseignants);
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
      this.enseignantService.deleteEnseignant(event.data.id).subscribe(data => {
          
      });
    }else{
      event.confirm.reject();
    }
  }
  onCreateConfirm(event):void{
    console.log(event);
    this.enseignantService.saveEnseignantByAdmin(event.newData).subscribe(data => {
    }, error => {
      console.log("erreur create") ;
    });
  }
  onSaveConfirm(event):void{
    console.log("edit"+JSON.stringify(event.newData));
    this.enseignantService.updateEnseignant(event.newData).subscribe(data => {
      console.log("enseignant ajouter avec succée");
      
    }, error => {
      console.log("erreur save") ;
    });

  }
}
