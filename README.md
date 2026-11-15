<p align="center">
  <img src="docs/media/pod-music-banner.svg" alt="Pod Music" width="720" />
</p>

<h1 align="center">Pod Music</h1>

<p align="center"><b>The wheel is back.</b> An Android music player that lives inside one beautifully engineered device.</p>

<p align="center">
  <img alt="Downloads" src="https://img.shields.io/github/downloads/nkosanamahungela-dev/Pod-music/total?label=Downloads&logo=github&logoColor=white&color=2563eb&style=for-the-badge" />
  <a href="https://github.com/nkosanamahungela-dev/Pod-music/releases"><img alt="Latest release" src="https://img.shields.io/github/v/release/nkosanamahungela-dev/Pod-music?label=Latest%20release&logo=github&logoColor=white&color=8b5cf6&style=for-the-badge" /></a>
  <img alt="Android 10 or later" src="https://img.shields.io/badge/Android-10%2B-3DDC84?logo=android&logoColor=white&style=for-the-badge" />
</p>

<p align="center"><a href="site/">Website</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="https://github.com/nkosanamahungela-dev/Pod-music/releases">Releases</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="docs/README.md">Documentation</a></p>

---

Every music app looks the same. Pod Music brings back the wheel: one wheel to move, one button to
choose, and everything about the device yours to change. Your own music comes first, an online
catalogue is built in, and there are no ads and no analytics.

## Releases

Pod Music has not been released yet. Until the first APK is published, build it from source using the instructions below. Future builds will be published in the [GitHub Releases](https://github.com/nkosanamahungela-dev/Pod-music/releases) page.

Needs Android 10 or later.

## What's inside

| | |
|---|---|
| **One wheel** | Turn to move, press to choose, Menu to go back. Every turn clicks under your thumb. |
| **Cover Flow** | Your albums in perspective, the centre one facing you, its neighbours standing to either side. |
| **Lyrics** | Time-synced and revealed word by word, full screen, in the typeface you choose. |
| **Offline first** | Your own library, sorted by album and artist, with a database that remembers your queue. |
| **Online built in** | Search and stream a full catalogue with your own account. No ads. |
| **Background play** | The music keeps going when you leave, with lock-screen and Bluetooth controls. |
| **A real device** | A boot self-test and startup chord, a power button, battery and disk lights. |
| **Private** | No analytics, no crash reporters. Stickers are cut out on your phone. |

## Make it yours

- **17 typefaces** for the whole display, from Classic to Gothic, plus **7 faces just for lyrics**.
- **16.7 million colours.** Dial any finish with the wheel and watch the body change live.
- **6 finishes:** Glass, Silver, Steel gray, Burgundy, Glacier blue or your own colour.
- **4 display themes:** Glass, Carbon, Bone and Custom, with your own picture behind it all.
- **Glitter** set into the body that catches the light as you tilt your phone.
- **Stickers** cut from your photos, stuck anywhere on the device, even over the screen.
- **Step outside:** pinch to lift your Pod Music out of the screen and hold it like an object.

## Build from source

Requirements: JDK 21 and the Android SDK.

```bash
./gradlew :app:assembleDebug     # app.podmusic.debug, with test tones and debug commands
./gradlew :app:assembleRelease   # app.podmusic, minified; signed when keystore.properties exists
```

Release signing reads `keystore.properties` (never committed) or `PODIUM_KEYSTORE_*` environment
variables; see [docs/deployment.md](docs/deployment.md). Architecture, decisions and testing live in
[docs/](docs/README.md). The project website source is in [site/](site/README.md).

## License

Pod Music is free software, licensed under the [GNU General Public License v3.0](LICENSE).

It builds on [NewPipeExtractor](https://github.com/TeamNewPipe/NewPipeExtractor) (GPL-3.0), Google's
MediaPipe MagicTouch model and the LiteRT runtime (Apache-2.0), AndroidX and Media3 (Apache-2.0),
Kyant's Backdrop and Shapes for the Glass theme (Apache-2.0), a Kotlin port of Apple's ALAC decoder
(Apache-2.0), and typefaces under the SIL Open Font License. Notices and license texts are in
[third_party/](third_party/). Pod Music is an independent project based on the upstream Podium codebase; it is not affiliated with any music
service or hardware maker; online services are used under their own terms.

**Developer**
Made by **Smelokuhle Mahungela**
