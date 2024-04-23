import { Component, OnInit } from '@angular/core';
import { DepartementService } from '../../services/departement.service';
import { Departement } from '../../models/Departement';
import { EtudiantService } from '../../services/etudiant.service';
import { MatiereService } from '../../services/matiere.service';
import { NbDialogService } from '@nebular/theme';
import { DialogDepartementComponent } from './dialog-departement/dialog-departement.component';

@Component({
  selector: 'ngx-departement',
  templateUrl: './departement.component.html',
  styleUrls: ['./departement.component.scss']
})
export class DepartementComponent implements OnInit{
  currentDate : string;
  departements: Departement[] = [];
  totalDep : number = 0;
  totalClasse : number = 0;
  totalEtd : number = 0;
  totalMatiere : number = 0;
  dep : Departement = new Departement();
  constructor(private departementService:DepartementService,private etudiantService : EtudiantService,
              private matiereService : MatiereService,private dialogService: NbDialogService
  ) { }
  ngOnInit(): void {
    this.matiereService.getAllMatieres().subscribe(data => {  
      this.totalMatiere = data.length;
    });
    this.currentDate = new Date().toLocaleDateString();
    this.departementService.getAllDepartements().subscribe(data => {  
      this.departements = data;
      this.totalDep = this.departements.length;
      this.departements.forEach(element => {  
        this.totalClasse += element.classes.length;
        element.classes.forEach(classe => { 
          this.etudiantService.getAllEtudiantsByClasse(classe.id).subscribe(data => {
            this.totalEtd += data.length;
          });

        })
        }); 
    });
  }

  getDepartmentColor1(department: string): string {
    if (department.toLowerCase().includes('inform')) {
      return '#fee4cb';
    }else if(department.toLowerCase().includes('gsil')){
      return '#e9e7fd';
    }
    else if(department.toLowerCase().includes('infotronique')){
      return 'ffd3e2'
    }
    else if(department.toLowerCase().includes('mecatr')){
      return '#c8f7dc;'
    }else return '#d5deff'
  }
  getDepartmentColor2(department: string): string {
    if (department.toLowerCase().includes('inform')) {
      return '#ff942e';
    }else if(department.toLowerCase().includes('gsil')){
      return '#4f3ff0';
    }
    else if(department.toLowerCase().includes('infotronique')){
      return '#df3670';
    }
    else if(department.toLowerCase().includes('mecatr')){
      return '#34c471'
    }else return '#4067f9'
  }

  getClasse(departement){
    return departement.classes.length*15;
  }
  getNbre(nb)
  {
    return Math.round((nb/this.totalClasse)*100);
  }

  open3() {
    this.dialogService.open(DialogDepartementComponent)
      .onClose.subscribe(ad => {   
        console.log(ad);
        this.dep.nom = ad; 
        this.departementService.addNewDepartement(this.dep).subscribe(data => {
          this.departements.push(data);
          this.departements = [...this.departements, data];
        });
      });
  }
}
