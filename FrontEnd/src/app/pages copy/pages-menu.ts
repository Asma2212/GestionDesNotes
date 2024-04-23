import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
  // {
  //   title: 'Accueil',
  //   icon: 'home-outline',
  //   link: '/admin/home',
  //   home : true
  // },
  {
    title: 'Dashboard Admin',
    icon: 'browser-outline',
    link: '/admin/dashboard',
    home : true
  },
  {
    title: 'Autres',
    group: true,
  },
  {
    title: 'Réglement',
    icon: 'layout-outline',
    link: '/admin/reglement',
  },
  {
    title: 'Les Administrateurs',
    icon: 'shield-outline',
    link: '/admin/admins',
  },
  {
    title: 'Les Matieres',
    icon: 'book-outline',
    link: '/admin/matieres',
  },
  {
    title: 'Les Classes',
    icon: 'layers-outline',
    link: '/admin/classes',
  },
  {
    title: 'Les Départements',
    icon: 'npm-outline',
    link: '/admin/departements',
  },
  {
    title: 'Les Enseignants',
    icon: 'people-outline',
    link: '/admin/enseignants',
  },
  {
    title: 'Les Etudiants',
    icon: 'award-outline',
    link: '/admin/etudiants',
  },
  {
    title: 'Authentification',
    icon: 'lock-outline',
    children: [
      {
        title: 'Connexion Etudiant',
        icon : 'log-in-outline',
        link: '/etudiant/connect/login',
      },
      {
        title: 'Connexion Enseignant',
        icon : 'log-in-outline',
        link: '/enseignant/connect/login',
      },
      {
        title: 'S\'inscrire',
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
        link: '/admin/layout/stepper',
      },
      {
        title: 'List',
        link: '/admin/layout/list',
      },
      {
        title: 'Infinite List',
        link: '/admin/layout/infinite-list',
      },
      {
        title: 'Accordion',
        link: '/admin/layout/accordion',
      },
      {
        title: 'Tabs',
        pathMatch: 'prefix',
        link: '/admin/layout/tabs',
      },
    ],
  },
  {
    title: 'Forms',
    icon: 'edit-2-outline',
    children: [
      {
        title: 'Form Inputs',
        link: '/admin/forms/inputs',
      },
      {
        title: 'Form Layouts',
        link: '/admin/forms/layouts',
      },
      {
        title: 'Buttons',
        link: '/admin/forms/buttons',
      },
      {
        title: 'Datepicker',
        link: '/admin/forms/datepicker',
      },
    ],
  },
  {
    title: 'UI Features',
    icon: 'keypad-outline',
    link: '/admin/ui-features',
    children: [
      {
        title: 'Grid',
        link: '/admin/ui-features/grid',
      },
      {
        title: 'Icons',
        link: '/admin/ui-features/icons',
      },
      {
        title: 'Typography',
        link: '/admin/ui-features/typography',
      },
      {
        title: 'Animated Searches',
        link: '/admin/ui-features/search-fields',
      },
    ],
  },
  {
    title: 'Modal & Overlays',
    icon: 'browser-outline',
    children: [
      {
        title: 'Dialog',
        link: '/admin/modal-overlays/dialog',
      },
      {
        title: 'Window',
        link: '/admin/modal-overlays/window',
      },
      {
        title: 'Popover',
        link: '/admin/modal-overlays/popover',
      },
      {
        title: 'Toastr',
        link: '/admin/modal-overlays/toastr',
      },
      {
        title: 'Tooltip',
        link: '/admin/modal-overlays/tooltip',
      },
    ],
  },
  {
    title: 'Extra Components',
    icon: 'message-circle-outline',
    children: [
      {
        title: 'Calendar',
        link: '/admin/extra-components/calendar',
      },
      {
        title: 'Progress Bar',
        link: '/admin/extra-components/progress-bar',
      },
      {
        title: 'Spinner',
        link: '/admin/extra-components/spinner',
      },
      {
        title: 'Alert',
        link: '/admin/extra-components/alert',
      },
      {
        title: 'Calendar Kit',
        link: '/admin/extra-components/calendar-kit',
      },
      {
        title: 'Chat',
        link: '/admin/extra-components/chat',
      },
    ],
  },
  {
    title: 'Charts',
    icon: 'pie-chart-outline',
    children: [
      {
        title: 'Echarts',
        link: '/admin/charts/echarts',
      },
      {
        title: 'Charts.js',
        link: '/admin/charts/chartjs',
      },
      {
        title: 'D3',
        link: '/admin/charts/d3',
      },
    ],
  },
  {
    title: 'Tables & Data',
    icon: 'grid-outline',
    children: [
      {
        title: 'Smart Table',
        link: '/admin/tables/smart-table',
      },
      {
        title: 'Tree Grid',
        link: '/admin/tables/tree-grid',
      },
    ],
  },
  /*{
    title: 'Miscellaneous',
    icon: 'shuffle-2-outline',
    children: [
      {
        title: '404',
        link: '/admin/miscellaneous/404',
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
