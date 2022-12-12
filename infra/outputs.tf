output "instance_url" {
    value       = aws_instance.backend.public_dns
}

output "s3_endpoint" {
    value = aws_s3_bucket.bucketfrontend.website_endpoint
}

output "s3_img_endpoint" {
    value = aws_s3_bucket.bucketimages.website_endpoint
}