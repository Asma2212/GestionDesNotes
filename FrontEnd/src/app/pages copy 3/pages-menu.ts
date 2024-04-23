import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
  // {
  //   title: 'Accueil',
  //   icon: 'home-outline',
  //   link: '/admin/home',
  //   home : true
  // },
  {
    title: 'Dashboard Enseignant',
    icon: 'browser-outline',
    link: '/enseignant/dashboard',
    home : true
  },
  {
    title: 'Autres',
    group: true,
  },
  {
    title: 'Réglement',
    icon: 'layout-outline',
    link: '/enseignant/reglement',
  },
  {
    title: 'Gestion des notes',
    icon: 'shield-outline',
    link: '/enseignant/notes',
  },
  {
    title: 'Les Matieres',
    icon: 'book-outline',
    link: '/enseignant/matieres',
  },
  {
    title: 'Les Classes',
    icon: 'layers-outline',
    link: '/enseignant/classes',
  },
  {
    title: 'Les Départements',
    icon: 'npm-outline',
    link: '/enseignant/departements',
  },
  {
    title: 'Les Enseignants',
    icon: 'people-outline',
    link: '/enseignant/enseignants',
  },
  {
    title: 'Les Etudiants',
    icon: 'award-outline',
    link: '/enseignant/etudiants',
  },
  {
    title: 'Authentification',
    icon: 'lock-outline',
    children: [
      {
        title: 'Se connecter',
        icon : 'log-in-outline',
        link: '/enseignant/connect/login',
      },
      {
        title: 'Ajouter Admin',
        icon : 'plus-outline',
        link: '/enseignant/connect/register',
      }
    ],
  },
 
];
