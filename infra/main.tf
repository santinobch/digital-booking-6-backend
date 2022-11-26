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

#ssh -i "llave.pem" ec2-user@ec2-18-188-157-88.us-east-2.compute.amazonaws.com
#Llave de acceso
resource "aws_key_pair" "deployer" {
  key_name   = "deployer-key"
  public_key = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQCzc/69117MBhUWTanreucuZfS/hy3BGTyvLbBvW6tTCxrGNXsG9usVFPZC6ZxmoKdAvPzW86cInHQM4+fB5k0mF98L6ye94VZ+ai8HUyFkeEJsvRtedCVXQrKIywjZohRVZZBFd/jn45bF06idl1uHbw3mnClMAlj79soyFe2L/kXTqDmzzi+PkupS44n2EDwVz9+qEyJ9GIeIpUg0H6NQ0EUKDflt3ryJp2xvRl1TO+j89AAQM9F7S7oor4+jaB4XO2W7riOoUMDjUzKBQShhiaRY2rlj/GHm/QsaA+yMBDz9TjFjjGsBGixRsArfhgGTX/BRkppUr3HnV4zS1sXGMZlftlsqjB4F3AWngxt0ow5bbs+79r6rkAXX2YiEDf/kOe3CKSIIuqKC+KTbEki3espGi+Bl6wVVDbEsrvMEKGK5j3I/Si5Y1hBafXXHny+HNf7uXyJvphvZMMG0HVQm2bfstXNsFGtXs5VvK6K1haOc9xf1uMDpeLiQrqfd6F8= oscar@OscarDraws"
}

# EC2 For Backend
resource "aws_instance" "backend" {
  ami           = "ami-0beaa649c482330f7"
  instance_type = "t2.micro"
  key_name      =  aws_key_pair.deployer.key_name

  user_data = data.template_file.init.rendered

  tags = {
    Name = "0521ptc8n2-grupo6-backend"
  }
}

#Bucket S3 for Frontend
resource "aws_s3_bucket" "bucketfrontend" {
  bucket        = "0521ptc8n2-grupo6-bk-frontend"

  tags = {
    Name        = "0521ptc8n2-grupo6-bk-frontend"
  }
}

# S3 For Frontent
resource "aws_s3_bucket_acl" "frontend" {
  bucket        = aws_s3_bucket.bucketfrontend.id
  acl           = "private"
}

#Bucket S3 for Images
resource "aws_s3_bucket" "bucketimages" {
  bucket        = "0521ptc8n2-grupo6-bk-images"

  tags = {
    Name        = "0521ptc8n2-grupo6-bk-images"
  }
}
# S3 For Images
resource "aws_s3_bucket_acl" "images" {
  bucket        = aws_s3_bucket.bucketimages.id
  acl           = "private"
}