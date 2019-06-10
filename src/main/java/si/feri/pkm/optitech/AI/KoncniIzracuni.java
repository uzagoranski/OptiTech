package si.feri.pkm.optitech.AI;

public class KoncniIzracuni {

	//Točnost
		public static double stPravilnih(int[][] matrikaStP) {
			double stPravilnih=0;
			for (int i=0; i<matrikaStP.length;i++){
				stPravilnih=stPravilnih+matrikaStP[i][i];
			}
			return stPravilnih;
		}
		
		//F_Score
		public static double fScore(int[][] matrikaFS) {
			double fscore=2*(precision(matrikaFS)*recall(matrikaFS))/(precision(matrikaFS)+recall(matrikaFS));
			return fscore;
		}

		//Precision
        public static double precision(int[][] matikaPre) {
		    double vseVrednost = 0;
            double precisionKoncano = 0;
            double precision2 = 0;
            for (int i=0;i<matikaPre.length;i++){
                double precision=0;
                double tp=matikaPre[i][i];
                double fptn=0;
                for (int j=0;j<matikaPre.length;j++){
                    fptn=fptn+matikaPre[j][i];
                }
                for(int k=0;k<matikaPre.length;k++)
                {
                    for(int a=0;a<matikaPre[k].length;a++){
                        vseVrednost+= matikaPre[k][a];
                    }
                }
                precision=precision+tp/(fptn);

                precision2+= (tp/vseVrednost) * precision;

            }
            precisionKoncano= precision2; // povprecje
            //vsiPravi/vsi * FmeraA
            return precisionKoncano;
        }


		//Recall
		public static double recall(int[][] matrika) {
			double rec=0;
			for (int i=0;i<matrika.length;i++){
				double tp=matrika[i][i];
				int fntp=0;
				for (int j=0;j<matrika[i].length;j++){
					fntp=fntp+matrika[i][j];
				}
				rec=rec+tp/fntp;
			}
			rec=rec/matrika.length;
			return rec;
		}

}
