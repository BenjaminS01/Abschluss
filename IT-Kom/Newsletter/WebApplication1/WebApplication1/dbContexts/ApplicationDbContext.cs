using Microsoft.EntityFrameworkCore;
using Newsletter.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Newsletter.dbContexts
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext>options) : base(options)
        {
                
        }
        public DbSet<News> MyProperty { get; set; }
    }
}
