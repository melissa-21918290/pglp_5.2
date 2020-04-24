package pglp_5.exo5_1;

import java.io.Serializable;



public class NumeroTel implements Serializable {



		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private  String descriptif;
		private  String numero;
		
		public NumeroTel( String descriptif, String numero) {
			this.descriptif=descriptif;
			this.numero=numero;
		}

		public String getDescriptif() {
			return descriptif;
		}
			//recuperer le numero de telephone
		public String getNumero() {
			return numero;
		}
		//recuperer le descriptif et le numero
		public String toString(){
			return this.getDescriptif()+":"+this.getNumero();
		}
		
		
		    @Override
		    public boolean equals(Object obj) {
		        if (this == obj)
		            return true;
		        if (obj == null)
		            return false;
		        if (getClass() != obj.getClass())
		            return false;
		        NumeroTel other = (NumeroTel) obj;
		        if (descriptif == null) {
		            if (other.descriptif != null)
		                return false;
		        } else if (!descriptif.equals(other.descriptif))
		            return false;
		        if (numero == null) {
		            if (other.numero != null)
		                return false;
		        } else if (!numero.equals(other.numero))
		            return false;
		        return true;
		    }
	}