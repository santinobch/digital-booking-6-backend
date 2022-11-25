#postman https://app.getpostman.com/join-team?invite_code=8b745fcf2d1351299a36fff26a6090d8
provider "aws" {
  region        = "us-east-2"
  access_key    = var.pi_access_key
  secret_key    = var.pi_secret_key
}

terraform {
  backend "s3" {
    bucket = "0521ptc8n2-grupo6-terraform-state"
    key    = "terraform.tfstate"
    region = "us-east-2"
  }
}

data "template_file" "init" {
  template = "${file("${path.module}/scripts/user-data.sh")}"
}

# EC2 For Backend
resource "aws_instance" "backend" {
  ami           = "ami-0beaa649c482330f7"
  instance_type = "t2.micro"

  user_data = data.template_file.init.rendered

  tags = {
    Name = "0521PTC8N2-grupo6-Backend"
  }
}

#Bucket S3 for Frontend
resource "aws_s3_bucket" "bucketFrontend" {
  bucket        = "0521PTC8N2-grupo6-bk-Frontend"

  tags = {
    Name        = "0521PTC8N2-grupo6-bk-Frontend"
  }
}

# S3 For Frontent
resource "aws_s3_bucket_acl" "Frontend" {
  bucket        = aws_s3_bucket.bucketFrontend.id
  acl           = "private"
}

#Bucket S3 for Images
resource "aws_s3_bucket" "bucketImages" {
  bucket        = "0521PTC8N2-grupo6-bk-Images"

  tags = {
    Name        = "0521PTC8N2-grupo6-bk-Images"
  }
}
# S3 For Images
resource "aws_s3_bucket_acl" "images" {
  bucket        = aws_s3_bucket.bucketImages.id
  acl           = "private"
}