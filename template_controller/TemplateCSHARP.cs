using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace ##Namespace##
{
    [Route("[controller]")]
    public class ##EntityName##Controller : ControllerBase
    {
        private readonly I##EntityName##Service _##EntityName##Service;

        public ##EntityName##Controller(I##EntityName##Service ##EntityName##Service)
        {
            _##EntityName##Service = ##EntityName##Service;
        }

        [HttpGet]
        public IEnumerable<##EntityName##> Get()
        {
            return _##EntityName##Service.GetAll##EntityName##();
        }

        [HttpGet("{id}")]
        public ##EntityName## Get(int id)
        {
            return _##EntityName##Service.Get##EntityName##ById(id);
        }

        [HttpPost]
        public ##EntityName## Post([FromBody] ##EntityName## ##EntityNameLowerCamelCase##)
        {
            return _##EntityName##Service.Create##EntityName##(##EntityNameLowerCamelCase##);
        }

        // ... autres méthodes pour les opérations CRUD

        [HttpDelete("{id}")]
        public void Delete(int id)
        {
            _##EntityName##Service.Delete##EntityName##(id);
        }
    }
}
