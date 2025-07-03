# LLD Question: Shape Rendering System (Bridge Pattern)

## Problem Statement:
You are building a Shape Drawing Application.

You have:

- Multiple types of shapes like `Circle`, `Rectangle`, etc.
- These shapes can be rendered in different ways, such as:

  - Vector-based rendering (`VectorRenderer`)
  - Raster-based rendering (`RasterRenderer`)
## Current Problem:
If you naively model this with inheritance, you end up with:

- VectorCircle
- RasterCircle
- VectorRectangle
- RasterRectangle  
This leads to a combinatorial explosion of classes as new shapes and renderers are added.

## Requirement:
✅ You need to design the system using the **Bridge Pattern**, so that:

✔ Shape hierarchy is independent of rendering implementation.

✔ You can add new shapes or new rendering types without modifying existing code.

✔ Shapes use composition to interact with renderers.

## Tasks:
- Design the class structure applying the Bridge Pattern.
- Show how a Circle can be drawn using both VectorRenderer and RasterRenderer.
- Explain how this design allows easy future extensions.