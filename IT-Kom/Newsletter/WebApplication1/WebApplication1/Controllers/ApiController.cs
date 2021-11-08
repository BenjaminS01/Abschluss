using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Newsletter.Controllers
{
    [ApiController]
    public class ApiController : ControllerBase
    {


        [HttpGet]
        [Route("/test")]
        public  string Get()
        {
           

            return "test";
        }
    }
}
