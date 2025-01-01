export interface Laboratoire {
  id: number;
  nom: string;
  logo: string;
  nrc: string;
  isActive: boolean;
  dateActivation: string | null;
}
