<p align="center">
  <a href="" rel="noopener">
 <img src="https://image.flaticon.com/icons/svg/1803/1803092.svg"  width="200" alt="Shorten Url Demo"></a>
</p>
<h1 align="center">Shorten Url Demo</h1>

<div align="center">

[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)]()
[![Status](https://img.shields.io/badge/status-active-success.svg)]()

</div>

<p align="center"> A complete shorten url application
    <br> 
</p>

## Table of Contents

- [Table of Contents](#table-of-contents)
- [Demo](#demo)
- [Features](#features)
- [Design](#design)
- [Limitation](#limitation)
- [Redis](#redis)
- [CI/CD](#CI/CD)
- [Kubernetes](#kubernetes)
- [Core-ng](#core-ng)
- [Spring Boot](#spring-boot)
- [Actix-web](#actix-web)
- [Authors](#authors)

## Demo


## Features

- Add shorten url
- IP limiting (5 call for 1 hour)
- Redirect shorten url

## Design

Use a distributed counter to encode the url to prevent duplicated url, and use a map to save all encoded and decoded url to fast look up.
The whole design is for simplicity only, many other concerns like partitions, high available, CDN and more should be considered before production.

## Limitation

- Non random url
- Single redis can only have 2^32 keys, so may need partitions design
- Single counter may become bottleneck

## Statistic

Suppose we allow 6 characters only, there will be 62^6 (56800235584) possibles combinations for encoded url.

- HashTable for lookup: 62^6 * (2048 + 6) ~= 107 TB (*2048 longest url)

## Analysis

Randomness
- Pre-generated combinations (Need large resource)
- Hash the url (Need conflict resolve)

Scalable
- Use a scalable database instead of redis, better for partitioning, persistent and replication
- A proper setup for CDN can reduce load from your application to CDN network

## Redis

- Cache for shorten url mapping
- Cache for ip
- Safe incremental counter

## CI/CD

## Kubernetes

## Core-ng

## Spring Boot

## Actix-web

## Authors

- [@hokamc](https://github.com/hokamc)

