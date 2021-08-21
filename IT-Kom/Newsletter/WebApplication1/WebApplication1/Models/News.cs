using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Newsletter.Models
{
    public class News
    {

        [Key]
        public int newsId { get; set; }

        [Required]
        public String titel { get; set; }

        public String autor { get; set; }
    }
}
