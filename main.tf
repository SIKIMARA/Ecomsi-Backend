provider "aws" {
  region = "eu-west-3"
}

variable "vpc_cidr_block" {}
variable "subnet_cidr_block" {}
variable "env_prefix" {}
variable "my_ip" {}
variable "public_key" {}
variable "private_key_path" {}

# VPC
resource "aws_vpc" "developement-vpc" {
  cidr_block = var.vpc_cidr_block
  tags = {
    Name = "${var.env_prefix}-vpc"
  }
}

# SUBNET
resource "aws_subnet" "dev-subnet-1" {
  vpc_id           = aws_vpc.developement-vpc.id
  cidr_block       = var.subnet_cidr_block
  availability_zone = "eu-west-3a"
  tags = {
    Name = "${var.env_prefix}-subnet"
  }
}

# INTERNET GATEWAY
resource "aws_internet_gateway" "myapp_igw" {
  vpc_id = aws_vpc.developement-vpc.id
  tags = {
    Name = "${var.env_prefix}-igw"
  }
}

data "aws_ami" "latest-amazon-linux-image" {
  most_recent = true
  owners = ["amazon"]
  filter {
    name   = "name"
    values = ["amzn2-ami-hvm-*-x86_64-gp2"]
  }
  filter {
    name = "virtualization-type"
    values = ["hvm"]
  }
}

resource "aws_key_pair" "name" {
  key_name   = "server-key"
  public_key = var.public_key
}

# INSTANCE

 
  

resource "aws_instance" "myapp_instance" {
  ami           = data.aws_ami.latest-amazon-linux-image.id
  instance_type = "t2.large"
  subnet_id     = aws_subnet.dev-subnet-1.id
  key_name      = aws_key_pair.name.key_name
  associate_public_ip_address = true


  tags = {
    Name = "${var.env_prefix}-instance"
  }

}
resource "aws_instance" "myapp_instance2" {
  ami           = data.aws_ami.latest-amazon-linux-image.id
  instance_type = "t2.large"
  subnet_id     = aws_subnet.dev-subnet-1.id
  key_name      = aws_key_pair.name.key_name
  associate_public_ip_address = true


  tags = {
    Name = "${var.env_prefix}-instance2"
  }

}


resource "aws_network_interface" "new_eni" {
  subnet_id = aws_subnet.dev-subnet-1.id
  # Add other ENI configuration options here
}

resource "aws_network_interface_attachment" "eip_attachment" {
  instance_id          = aws_instance.myapp_instance.id
  network_interface_id = aws_network_interface.new_eni.id
  device_index         = 1 # Use an appropriate device index
}
resource "aws_security_group" "myapp_sg" {
  name_prefix = "${var.env_prefix}-default-sg"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = [var.my_ip]
  }

  # Adjust this rule as needed to restrict access to port 8080.
  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]  # Allow all sources (adjust for production)
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
    prefix_list_ids = []
  }

  vpc_id = aws_vpc.developement-vpc.id
}
output "public_dns" {
  value = aws_instance.myapp_instance.public_dns
}


output "instance_ip_address" {
  value = aws_instance.myapp_instance.public_ip
}

output "dev_subnet_1" {
  value = aws_subnet.dev-subnet-1.id
}

output "dev_vpc" {
  value = aws_vpc.developement-vpc.id
}

output "dev_igw" {
  value = aws_internet_gateway.myapp_igw.id
}
