//
// Created by qihoo on 2022/4/29.
//

#ifndef FFMPEG_TRIANGLESAMPLE_H
#define FFMPEG_TRIANGLESAMPLE_H


#include "GLSampleBase.h"

class TriangleSample : public GLSampleBase
{
public:
    TriangleSample();
    virtual ~TriangleSample();

    virtual void LoadImage(NativeImage *pImage);

    virtual void Init();

    virtual void Draw(int screenW, int screenH);

    virtual void Destroy();
};

#endif //FFMPEG_TRIANGLESAMPLE_H
