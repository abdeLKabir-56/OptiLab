import { Epreuve } from "./analysis";

export interface Analyse {
  id: number; // Identifiant unique de l'analyse
  laboratoire: string; // Nom du laboratoire
  nom: string; // Nom de l'analyse
  description: string; // Description de l'analyse
  status: "resolved" | "pending"; // État de l'analyse (résolu ou en attente)
  username:string,
  epreuves: Epreuve[]; // Liste des épreuves associées
}
