import pygame
from pygame.locals import *

import math

from OpenGL.GL import *
from OpenGL.GLU import *

R = float(input("R = "))

verticies = []
edges = []
x = 0
y = 0
z = 0
prev = None
fi = -math.pi
while fi <= math.pi:
    teta = 0
    while teta <= 2*math.pi:
        x = R*math.sin(fi)*math.cos(teta)
        y = R*math.sin(fi)*math.sin(teta)
        z = R*math.cos(fi)
        if (x >= -R/6 and x <= R/6 and y >= -R and y <= R and z >= -R and z <= R):
            if teta == 0: first = [x, y, z]
            curr = [x, y, z]
            verticies.append(curr)
            if(prev): edges.append([verticies.index(prev), verticies.index(curr)])
            prev = curr
        teta += 0.05
    if (prev and first): edges.append([verticies.index(prev), verticies.index(first)])
    first = None
    prev = None
    fi += math.pi/30;


def Cube():
    glBegin(GL_LINES)
    for edge in edges:
        for vertex in edge:
            glVertex3fv(verticies[vertex])
    glEnd()


def main():
    pygame.init()
    display = (800,600)
    pygame.display.set_mode(display, DOUBLEBUF|OPENGL)

    gluPerspective(45, (display[0]/display[1]), 0.1, 50.0)

    glTranslatef(0.0,0.0, -4)

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()

        glRotatef(3, 3, 1, 1)
        glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT)
        Cube()
        pygame.display.flip()
        pygame.time.wait(10)


main()
