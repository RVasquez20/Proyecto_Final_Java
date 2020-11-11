using System.ComponentModel.DataAnnotations;


namespace Productos.Models{
    public class Productos{
        [Key]
        public int idProducto{get; set;}
         public string producto{get; set;}
         public string idmarca{get; set;}
        public string Descripcion{get; set;}
        public string Imagen{get; set;}
        public float precio_costo{get; set;}
        public float precio_venta{get; set;}
        public int existencia{get; set;}
        public string fecha_ingreso{get; set;}
              

    }
}
