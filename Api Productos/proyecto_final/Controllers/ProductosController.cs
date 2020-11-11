using Microsoft.AspNetCore.Mvc;
using System.Linq;
using Productos.Models;
namespace Productos.Controllers{
     [Route("api/[controller]")]
    public class ProductosController : Controller{
        private Conexion dbConexion;

        public ProductosController(){
            dbConexion = Conectar.Create();
        }
       [HttpGet]
       public ActionResult Get(){
           return Ok(dbConexion.Productos.ToArray());
       }
        [HttpGet ("{id}")]
        public ActionResult Get(int id){
            var Productos = dbConexion.Productos.SingleOrDefault(a => a.idProducto == id);
            if(Productos !=null){
                return Ok(Productos);
                
            }else
                {
                return NotFound();    
                }
                
        }

    }
    

}
