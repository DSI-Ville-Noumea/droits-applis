
var courant='';

// Constructeur de la classe Menu
function Menu() { 
//     Déclaration des variables membres (propriétés) 
    this.listeObjet = new Array();
    this.niveau=0;
//     Déclaration d'une fonction membre (méthode) 
    this.ajouterFils= ajouterObjet;
    this.incrementeFils = incrementeNiveau;
    this.afficher = afficherMenu;
     this.afficherhoriz = afficherMenuHoriz;
} 

// Implantation du code de la fonction membre 
function afficherMenu() { 
	var res = '';
//	parcouirs des objets et deb mande d'affichage
	for (var a in this.listeObjet) {
		res += this.listeObjet[a].afficher();
	}
	return res;
 }

function afficherMenuHoriz() { 
	var res = '';
//	parcouirs des objets et de mande d'affichage
	for (var a in this.listeObjet) {
		res += this.listeObjet[a].afficherhoriz();
	}
	return res;
 }
 
// Constructeur de la classe Lien
function Lien(aDroit, aTitreLien, aTitre, actif) { 
//     Déclaration des variables membres (propriétés) 
    this.droit= aDroit;
    this.titreLien=aTitreLien;
    this.titre= aTitre; 
    this.isActif = actif;
    this.niveau=0;
    this.type='lien';
//     Déclaration d'une fonction membre (méthode) 
    this.afficher= afficherLien; 
    this.afficherhoriz= afficherLienHoriz; 
} 

// Implantation du code de la fonction membre 
function afficherLien() {
	var trouve = false;
//	Vérif du droit de l'utilisateur
	for (var a in listeDroits) {
		if (listeDroits[a] == this.droit) {
			trouve=true;
		}
	}
	
//	Si droit trouvé
	if (trouve) {
		var comment='';
//		Si menu actif
		if (this.isActif) {
			classe = 'LienActif';
			onClic = 'envoieFormulaire(this); changerTitre(this.title);';
		} else {
			classe = 'LienInactif';
			onClic = '';
			comment=' EN CONSTRUCTION ';
		}

		var temp = 	'<span id="'+this.droit+'" class="'+classe+'" TITLE="'+this.titre+'" onClick="'+onClic+'">'+
				'<img src="images/menu_lien.gif"> '+this.titreLien+comment+'<br>'+
				'</span>';
		return temp;
	} else {
		return '';
	}
 } 

function afficherLienHoriz() {
	var trouve = false;
//	Vérif du droit de l'utilisateur
	for (var a in listeDroits) {
		if (listeDroits[a] == this.droit) {
			trouve=true;
		}
	}
	
	
//	Si droit trouvé
	if (trouve) {
		var comment='';
//		Si menu actif
		if (this.isActif) {
			classe = 'LienActif';
			onClick = 'envoieFormulaire(this); changerTitre(this.title);';
		} else {
			classe = 'LienInactif';
			onClick = '';
			comment=' EN CONSTRUCTION ';
		}

		var temp = 	'<span id="'+this.droit+'" class="'+classe+'" TITLE="'+this.titre+'" onClick="'+onClick+'">'+
				'<img src="images/menu_lien.gif"> '+this.titreLien+comment+'</span>'
				+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
		return temp;
	} else {
		return '';
	}
 } 

// Constructeur de la classe Dossier
function Dossier(aNom, aTitre) { 
//     Déclaration des variables membres (propriétés) 
    this.nom= aNom; 
    this.titre= aTitre;
    this.listeObjet = new Array();
    this.niveau=1;
    this.type='dossier';
//     Déclaration d'une fonction membre (méthode) 
    this.afficher= afficherDossier; 
    this.ajouterFils= ajouterObjet;
    this.incrementeFils = incrementeNiveau;
} 

//Ajoute un objet à la liste
function ajouterObjet (obj) {
   var objInc=this.incrementeFils(obj);
   var v = new Array(objInc);
   this.listeObjet = this.listeObjet.concat(v);
}

//incremente niveau
function incrementeNiveau(obj){
   obj.niveau=this.niveau + 1;
   if (obj.type=='dossier') {
//      parcours des éléments et incrémente niveau
	for (var a in obj.listeObjet) {
		var v = obj.incrementeFils(obj.listeObjet[a]);
		obj.listeObjet[a] = v;
	}

   }
   return obj;
}

// Implantation du code de la fonction membre 
function afficherDossier() { 
	var res;
//	res = 	'<span id="'+this.nom+'" onClick="showhide('+this.nom+'o,'+this.nom+'sign); changerTitre(this.title);" class="Dossier" TITLE="'+this.titre+'">'+
	res = 	'<span id="'+this.nom+'" onClick="showhide('+this.nom+'o,'+this.nom+'sign)" class="Dossier" TITLE="'+this.titre+'">'+
		'<font style="text-decoration:none"><IMG id="'+this.nom+'sign" src="images/menu_dossier_clos.gif"></font> '+this.titre+'</span><br>\n'+
		'<SPAN id="'+this.nom+'o" style="display:none">';
//	parcours des éléments et rajout
	var contenu = '';
	for (var a in this.listeObjet) {
		var temp = this.listeObjet[a].afficher();

		if (temp != '') {
			for (var i=1; i< this.listeObjet[a].niveau; i++) {
				contenu += '<IMG src="images/carre_vide.gif">';
			}
			contenu += temp;
		}
	}

//	Si pas de contenu on ne retourne rien
	if (contenu == '') {
		res = '';
	} else {

		res += contenu + '</SPAN>\n';
	}
	return res;
 } 

var Open = "";
var Closed = "";

var choix = '';

function preload(){
    if(document.images){
        Open = new Image(16,13);
        Closed = new Image(16,13);
        Open.src = "images/menu_dossier_ouvert.gif";
        Closed.src = "images/menu_dossier_clos.gif";
    }
}


function showhide(what,what2){
    if (what.style.display=='none'){
        what.style.display='';
        what2.src=Open.src;
    } else {
        what.style.display='none';
        what2.src=Closed.src;
    }
}

//Lance le formulaire en alimentant le lien
function envoieFormulaire(lien) {
	if (courant != '') {
//		courant.style.background="";
		courant.className="LienActif";
	}
		
	courant = lien;
//	lien.style.background="beige";
	courant.className="LienCourant";

	leForm.ACTIVITE.value = lien.id;
	document.leForm.submit();
}

//Change le titre de la barre des titres
function changerTitre(titre) {
	window.parent.frames("Titre").changerTitre(titre);
}