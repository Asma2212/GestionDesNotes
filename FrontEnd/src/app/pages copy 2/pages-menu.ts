import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
  {
    title: 'Accueil',
    icon: 'home-outline',
    link: '/etudiant/home',
    home : true
  },
  {
    title: 'Autres',
    group: true,
  },
  {
    title: 'Réglement',
    icon: 'layout-outline',
    link: '/etudiant/reglement',
  },
  {
    title: 'Les Départements',
    icon: 'npm-outline',
    link: '/etudiant/departements',
  },
  {
    title: 'Les Enseignants',
    icon: 'people-outline',
    link: '/etudiant/enseignants',
  },
  {
    title: 'Les Etudiants',
    icon: 'award-outline',
    link: '/etudiant/etudiants',
  },
  {
    title: 'Authentification',
    icon: 'lock-outline',
    children: [
      {
        title: 'Se connecter',
        icon : 'log-in-outline',
        link: '/etudiant/connect/login',
      },
      {
        title: 'Ajouter etudiant',
        icon : 'plus-outline',
        link: '/etudiant/connect/register',
      }
    ],
  },
  {
    title: 'Layout',
    icon: 'layout-outline',
    children: [
      {
        title: 'Stepper',
        link: '/etudiant/layout/stepper',
      },
      {
        title: 'List',
        link: '/etudiant/layout/list',
      },
      {
        title: 'Infinite List',
        link: '/etudiant/layout/infinite-list',
      },
      {
        title: 'Accordion',
        link: '/etudiant/layout/accordion',
      },
      {
        title: 'Tabs',
        pathMatch: 'prefix',
        link: '/etudiant/layout/tabs',
      },
    ],
  },
  {
    title: 'Forms',
    icon: 'edit-2-outline',
    children: [
      {
        title: 'Form Inputs',
        link: '/etudiant/forms/inputs',
      },
      {
        title: 'Form Layouts',
        link: '/etudiant/forms/layouts',
      },
      {
        title: 'Buttons',
        link: '/etudiant/forms/buttons',
      },
      {
        title: 'Datepicker',
        link: '/etudiant/forms/datepicker',
      },
    ],
  },
  {
    title: 'UI Features',
    icon: 'keypad-outline',
    link: '/etudiant/ui-features',
    children: [
      {
        title: 'Grid',
        link: '/etudiant/ui-features/grid',
      },
      {
        title: 'Icons',
        link: '/etudiant/ui-features/icons',
      },
      {
        title: 'Typography',
        link: '/etudiant/ui-features/typography',
      },
      {
        title: 'Animated Searches',
        link: '/etudiant/ui-features/search-fields',
      },
    ],
  },
  {
    title: 'Modal & Overlays',
    icon: 'browser-outline',
    children: [
      {
        title: 'Dialog',
        link: '/etudiant/modal-overlays/dialog',
      },
      {
        title: 'Window',
        link: '/etudiant/modal-overlays/window',
      },
      {
        title: 'Popover',
        link: '/etudiant/modal-overlays/popover',
      },
      {
        title: 'Toastr',
        link: '/etudiant/modal-overlays/toastr',
      },
      {
        title: 'Tooltip',
        link: '/etudiant/modal-overlays/tooltip',
      },
    ],
  },
  {
    title: 'Extra Components',
    icon: 'message-circle-outline',
    children: [
      {
        title: 'Calendar',
        link: '/etudiant/extra-components/calendar',
      },
      {
        title: 'Progress Bar',
        link: '/etudiant/extra-components/progress-bar',
      },
      {
        title: 'Spinner',
        link: '/etudiant/extra-components/spinner',
      },
      {
        title: 'Alert',
        link: '/etudiant/extra-components/alert',
      },
      {
        title: 'Calendar Kit',
        link: '/etudiant/extra-components/calendar-kit',
      },
      {
        title: 'Chat',
        link: '/etudiant/extra-components/chat',
      },
    ],
  },
  {
    title: 'Charts',
    icon: 'pie-chart-outline',
    children: [
      {
        title: 'Echarts',
        link: '/etudiant/charts/echarts',
      },
      {
        title: 'Charts.js',
        link: '/etudiant/charts/chartjs',
      },
      {
        title: 'D3',
        link: '/etudiant/charts/d3',
      },
    ],
  },
  {
    title: 'Tables & Data',
    icon: 'grid-outline',
    children: [
      {
        title: 'Smart Table',
        link: '/etudiant/tables/smart-table',
      },
      {
        title: 'Tree Grid',
        link: '/etudiant/tables/tree-grid',
      },
    ],
  },
  /*{
    title: 'Miscellaneous',
    icon: 'shuffle-2-outline',
    children: [
      {
        title: '404',
        link: '/etudiant/miscellaneous/404',
      },
    ],
  },*/
  {
    title: 'Authentification',
    icon: 'lock-outline',
    children: [
      {
        title: 'Se Connecter',
        link: '/auth/login',
      },
      {
        title: 'S\'inscrire',
        link: '/auth/register',
      },
      {
        title: 'Mot de passe oublié',
        link: '/auth/request-password',
      },
      {
        title: 'Changer Mot de passe',
        link: '/auth/reset-password',
      },
    ],
  },
];
