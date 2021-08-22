﻿// <auto-generated />
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Newsletter.dbContexts;

namespace Newsletter.Migrations
{
    [DbContext(typeof(ApplicationDbContext))]
    [Migration("20210821143713_AddNewsModelToDb")]
    partial class AddNewsModelToDb
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("ProductVersion", "5.0.9")
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("Newsletter.Models.News", b =>
                {
                    b.Property<int>("newsId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("autor")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("titel")
                        .IsRequired()
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("newsId");

                    b.ToTable("MyProperty");
                });
#pragma warning restore 612, 618
        }
    }
}