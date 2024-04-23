import { Component, Input, OnInit } from '@angular/core';
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder } from '@nebular/theme';
import { Etudiant } from '../../models/Etudiant';
import { EtudiantService } from '../../services/etudiant.service';
import { DepartementService } from '../../services/departement.service';
import { Departement } from '../../models/Departement';
import { ClasseService } from '../../services/classe.service';

@Component({
  selector: 'ngx-etudiants',
  templateUrl: './etudiants.component.html',
  styleUrls: ['./etudiants.component.scss']
})
export class EtudiantsComponent implements OnInit{
  etudiants: Etudiant[] = [];
  departements: string[] = [];
  etudiantDep : any;
  dep: string;

  constructor(private etudiantService:EtudiantService,private classeService : ClasseService){}
  ngOnInit(): void {
    this.etudiantService.getAllEtudiants().subscribe(data => {
      this.etudiants = data;
    
    this.etudiants.forEach(element => {
      this.classeService.getDepartementByClasseId(element.classe.id).subscribe(data => {
        this.departements[element.id]= data.nom;
      });
    });
  });
  }

  getDepartementStyle(id: number): any {
    var nom = this.departements[id];
    if (nom) {
      if (nom.toLowerCase().includes('inform')) {
        return { 
          'background-color': '#CC7793',
        };
      } else {
        if (nom.toLowerCase().includes('gsil')) {
          return { 
            'background-color': '#F0A8A8',
          };
        } else {
          if (nom.toLowerCase().includes('infotronique')) {
            return { 
              'background-color': '#F9B189',
            };
          }
        else{
          if (nom.toLowerCase().includes('mecatr')) {
            return { 
              'background-color': '#F6CD13',
            };
        }
      }
    }}} else {
      return {
            'background-color': 'grey',
      }; 
    }
  }
}