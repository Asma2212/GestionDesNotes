import { Component, OnInit } from '@angular/core';
import { DsService } from '../../services/ds.service';
import { ExamenService } from '../../services/examen.service';
import { EvaluationService } from '../../services/evaluation.service';
import { Matiere } from '../../models/Matiere';
import { MatiereService } from '../../services/matiere.service';
import { EtudiantService } from '../../services/etudiant.service';
import { Note } from '../../models/Note';
@Component({
  selector: 'ngx-resultat',
  templateUrl: './resultat.component.html',
  styleUrls: ['./resultat.component.scss']
})
export class ResultatComponent implements OnInit{
  mykey : Matiere = new Matiere();
  notesMatiere : Map<string,Set<Note>> = new Map<string, Set<Note>>();
  moyenneGenerale : number = 0;
  moyParMatiere: Map<string,number> = new Map<string, number>();
  rang : number = 0;
  constructor(private etudiantService:EtudiantService
    ,private matiereService:MatiereService) { }
  ngOnInit(): void {
    this.etudiantService.getMoyParMatiere(localStorage.getItem('username')).subscribe(data => {
      this.moyParMatiere = data;
      
    });
      this.etudiantService.getAllMatiereByEtudiant(localStorage.getItem('username')).subscribe(data => {
        this.notesMatiere = data;
    });
    this.etudiantService.getMoyenneGenerale(localStorage.getItem('username')).subscribe(data => {
      this.moyenneGenerale = data;
  });
  }
}