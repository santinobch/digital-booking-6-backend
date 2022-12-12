variable "pi_access_key" {
 type = string
 description = "Access key"
}
variable "pi_secret_key" {
 type = string
 description = "Access key"
}
variable "pi_public_rsa_key"{
    type = string
    description = "RSA public key"
}
variable "pi_bucket_front"{
    type = string
    description = "Bucket name Front"
}
variable "pi_bucket_img"{
    type = string
    description = "Bucket name Images"
}
variable "pi_name_back"{
    type = string
    description = "Bucket name Back"
}
variable "pi_ami_id"{
    type = string
    description = "id AMI"
}
variable "pi_region"{
    type = string
    description = "Region AWS"
}