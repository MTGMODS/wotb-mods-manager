# Blitz Mods (Android)

A mobile mod distribution platform for World of Tanks Blitz.

## Overview

This project was originally developed in 2021–2022 as a full-featured Android application for browsing, managing, and installing game mods.

The app included backend integration, content management, and monetization features.

## Features

* Mod catalog with categories (hangars, sights, skins, sounds, etc.)
* Mod installation system (file extraction into game directory)
* Firebase backend (Realtime Database + Storage)
* Admin & modder panel for content management
* Mod upload pipeline (user-generated content with moderation)
* APK auto-update system
* Remote config (feature flags, kill switch)
* Push notifications (OneSignal)
* Monetization (Unity Ads + VIP system)
* Gamification (ad disable via mini-game)
* Multi-language support (RU / EN)

## Tech Stack

* Android (Java)
* Firebase Realtime Database & Storage
* OneSignal
* Unity Ads

## Demo Notes

This repository contains a restored version of the project from a compiled APK.

Some features are disabled or simplified:

* File system access (SAF) is not functional on modern Android versions
* Mod installation is stubbed for demo purposes
* Firebase functionality may be partially unavailable

## Context

Originally built as a solo project without a PC using low-code tools (Sketchware), later imported into Android Studio.

The app reached an active user base and included a community-driven mod system.

## Purpose

This repository is published as a portfolio project demonstrating:

* product-oriented development
* backend integration
* mobile UI/UX design
* content platform architecture
