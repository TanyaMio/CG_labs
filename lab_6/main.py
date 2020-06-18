import pygame
from pygame.locals import *

from OpenGL.GL import *
from OpenGL.GLU import *

from math import *


print("Можливі обертання : 1) X\n                    2) \
X, Y\n                    3) X, Y, Z\n--------------------------------")
N = float(input("Введіть номер обертання : "))

verticies = [ [0,0,0],   [1,0,0], [0,0,-1], [1,2,0],
             [0,2,-1],[0,1.75,0],[0.25,2,0],[0,2,-0.25]]
edges = [[0,1],[0,2],[0,5],[1,2],
         [1,3],[2,4],[3,4],[3,6],
         [4,7],[5,6],[5,7],[6,7]]

def Sphere():
    glBegin(GL_LINES)
    for edge in edges:
        for vertex in edge:
            glVertex3fv(verticies[vertex])
    glEnd()


def main():
    pygame.init()
    display = (800,600)
    pygame.display.set_mode(display, DOUBLEBUF|OPENGL)
    pygame.display.set_caption("IV-81 Diachenko , Buhtiy : LAB_6")

    gluPerspective(45, (display[0]/display[1]), 0.1, 20.0)

    glTranslatef(0.0,0.0, -10)

    #gluLookAt(2,2,2,0,0,0,0,0,1)         # статичний вигляд

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
        if (N == 1.0) :
            glRotatef(1, 1, 0, 0)           # анімація 1
        elif (N == 2.0) :
            glRotatef(1, 1, 1, 0)           # анімація 2
        elif (N == 3.0) :
            glRotatef(1, 1, 1, 1)           # анімація 3
        #glRotate(1,5,6,7,8)
        glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT)
        Sphere()
        pygame.display.flip()
        pygame.time.wait(10)


main()
