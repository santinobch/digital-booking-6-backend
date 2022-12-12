provider "aws" {
  region        = var.pi_region
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

resource "aws_key_pair" "deployer" {
  key_name   = "deployer-key"
  public_key = var.pi_public_rsa_key
}

# EC2 For Backend
resource "aws_instance" "backend" {
  ami           = var.pi_ami_id
  instance_type = "t2.micro"
  key_name      =  aws_key_pair.deployer.key_name

  user_data = data.template_file.init.rendered

  tags = {
    Name = var.pi_name_back
  }
}

#Bucket S3 for Frontend
resource "aws_s3_bucket" "bucketfrontend" {
  bucket        = var.pi_bucket_front
  policy        = file("s3-policy.json")

  website {
    index_document  = "index.html"
  }

  tags = {
    Name            = var.pi_bucket_front
  }
}

# S3 For Frontent
resource "aws_s3_bucket_acl" "frontend" {
  bucket        = aws_s3_bucket.bucketfrontend.id
  acl           = "public-read"
}

#Bucket S3 for Images
resource "aws_s3_bucket" "bucketimages" {
  bucket        = var.pi_bucket_img
  policy        = file("s3-img-policy.json")

  tags = {
    Name        = var.pi_bucket_img
  }
}

# S3 For Images
resource "aws_s3_bucket_acl" "images" {
  bucket        = aws_s3_bucket.bucketimages.id
  acl           = "public-read"
}