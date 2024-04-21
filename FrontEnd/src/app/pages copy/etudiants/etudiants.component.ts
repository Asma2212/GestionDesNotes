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
      if (nom.toLowerCase().includes('info')) {
        return { 
          'background-color': 'red',
        };
      } else {
        if (nom.toLowerCase().includes('gsil')) {
          return { 
            'background-color': 'green',
          };
        } else {
          if (nom.toLowerCase().includes('infotronique')) {
            return { 
              'background-color': 'pink',
            };
          }
        else{
          if (nom.toLowerCase().includes('mecatr')) {
            return { 
              'background-color': 'orange',
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