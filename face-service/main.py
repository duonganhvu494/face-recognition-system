from fastapi import FastAPI, File, UploadFile
from fastapi.responses import JSONResponse
from PIL import Image
import numpy as np
import insightface
import io

app = FastAPI()

# Load model
model = insightface.app.FaceAnalysis(name="buffalo_l")
model.prepare(ctx_id=0)

known_embedding = np.random.rand(512).astype(np.float32)

@app.post("/recognize")
async def recognize(file: UploadFile = File(...)):
    try:
        image_bytes = await file.read()
        img = Image.open(io.BytesIO(image_bytes)).convert("RGB")
        img_np = np.array(img)

        faces = model.get(img_np)
        if not faces:
            return JSONResponse(content={
                "matched": False,
                "name": "Unknown",
                "confidence": 0.0
            })

        face = faces[0]
        embedding = face.embedding

        sim = float(np.dot(embedding, known_embedding) / (np.linalg.norm(embedding) * np.linalg.norm(known_embedding)))
        matched = sim > 0.5
        name = "Nguyễn Văn A" if matched else "Unknown"

        return {
            "matched": matched,
            "name": name,
            "confidence": round(sim, 4)
        }
    except Exception as e:
        return JSONResponse(status_code=500, content={"error": str(e)})
