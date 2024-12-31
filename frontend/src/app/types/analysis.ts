export interface Examen {
  resultat: string | null; // Le résultat peut être une chaîne ou null
  observations: string | null; // Les observations peuvent être une chaîne ou null
}

export interface Epreuve {
  id: string; // Identifiant unique de l'épreuve
  nom: string; // Nom de l'épreuve
  examen: Examen; // Informations sur l'examen
}

export interface Analyse {
  id: string; // Identifiant unique de l'analyse
  laboratoire: string; // Nom du laboratoire
  nom: string; // Nom de l'analyse
  description: string; // Description de l'analyse
  status: "resolved" | "pending"; // État de l'analyse (résolu ou en attente)
  epreuves: Epreuve[]; // Liste des épreuves associées
}
