import { Component, OnInit } from '@angular/core';
import { EtudiantService } from '../../services/etudiant.service';
import { Note } from '../../models/Note';
import { MatiereService } from '../../services/matiere.service';

@Component({
  selector: 'ngx-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.scss']
})
export class NoteComponent implements OnInit{
  notes :Note[] = [];
  constructor(private etudiantService:EtudiantService,private matiereService : MatiereService) { }
  ngOnInit(): void {
    this.etudiantService.getEtudiantByEmail(localStorage.getItem('username')).subscribe(data => {
      console.log(data);
      this.notes = data.notes;
  });
  }

  getCardColorClass(note: number) {
    if (note < 10) {
      return {
        'background-color': '#f8252570',
      };
    } else if (note >= 10 && note <= 14) {
      return {
        'background-color': '#ffb2193c', //#ffb2193c
      };
    } else {
      return {
        'background-color': '#1dff193c',
      };
    }
}

getDepartmentColor1(note: number): string {
  if (note<10) {
    return '#ff181849';
  }else if(note>=10 && note<14){
    return '#fee4cb';
  }
  else if(note>14){
    return '#c8f7dc'
  }
  return 'grey'
}
getDepartmentColor2(note: number): string {
  if (note<10) {
    return '#df3636';
  }else if(note>=10 && note<14){
    return '#ff942e';
  }
  else if(note>14){
    return '#34c471';
  }
  return 'grey'
}

}
