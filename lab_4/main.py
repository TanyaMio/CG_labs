from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt
import numpy as np

R = int(input("R = "))

# Create graphics z(x), z(y)
x, y, z = [], [], []
i = 0
while i <= 2 * np.pi:
    j = 0
    while j <= 2 * np.pi:
        x.append(round(R * np.sin(i) * np.cos(j), 2))
        y.append(round(R * np.sin(i) * np.sin(j), 2))
        z.append(round(R * np.sin(i), 2))
        j += 0.1
    i += 0.1

# Create graphics z(x), z(y)
fig1 = plt.figure()
ax1 = fig1.add_subplot(311)

ax1.plot(z, x, label=u'X', color='green')
ax1.set_ylabel(u'X')
ax1.set_xlabel(u'Z')
ax1.grid(True, color='green')
ax1.tick_params(axis='y', which='major', labelcolor='green')

ax2 = fig1.add_subplot(312)
ax2.plot(z, y, label=u'Y', color='red')
ax2.set_ylabel(u'Y')
ax2.set_xlabel(u'Z')
ax2.tick_params(axis='y', which='major', labelcolor='red')
ax2.grid(True, color='red')

ax3 = fig1.add_subplot(313)
ax3.plot(x, y, label=u'Y(x)', color='blue')
ax3.set_ylabel(u'Y')
ax3.set_xlabel(u'X')
ax3.tick_params(axis='y', which='major', labelcolor='blue')
ax3.grid(True, color='blue')

plt.show()

# Count x, y, z
x, y, z = [], [], []
i = 0
while i <= 2 * np.pi:
    j = 0
    while j <= 2 * np.pi:
        x.append(round(R * np.sin(i) * np.cos(j), 2))
        y.append(round(R * np.sin(i) * np.sin(j), 2))
        z.append(round(R * np.sin(i), 2))
        j += 1
    i += 1
# Create table
fig = plt.figure()
ax = plt.gca()
ax.axis('off')
data = [x,y,z]
columns = ('X', 'Y', 'Z')
the_table = plt.table(cellText=data, rowLabels=columns, loc='center')
the_table.auto_set_font_size(False)
the_table.set_fontsize(8)
plt.show()


# Count x, y, z
x, y, z = [], [], []
i = 0
while i <= 2 * np.pi:
    j = 0
    while j <= 2 * np.pi:
        x.append(round(np.sin(i) * np.cos(j), 2))
        y.append(round(np.sin(i) * np.sin(j), 2))
        z.append(round(np.sin(i), 2))
        j += 0.5
    i += 0.5

# Create gistograms

plt.bar(x, y, 0.02)

plt.ylabel('Y')
plt.xlabel('X')
plt.show()

plt.bar(x, z, 0.02)

plt.ylabel('Z')
plt.xlabel('X')
plt.show()

plt.bar(y, z, 0.02)

plt.ylabel('Z')
plt.xlabel('Y')
plt.show()



# Count x, y, z
x, y, z = [], [], []
i = 0
while i <= 2 * np.pi:
    j = 0
    while j <= 2 * np.pi:
        x.append(round(np.sin(i)*np.cos(j) , 2))
        y.append(round(np.sin(i)*np.sin(j), 2))
        z.append(round(np.sin(i), 2))
        j += 2
    i += 2

# Create pies
plt.title('X, Z')
plt.pie(z)
plt.legend(x)
plt.show()

plt.title('Y, Z')
plt.pie(z)
plt.legend(y)
plt.show()

plt.title('X, Y')
plt.pie(y)
plt.legend(x)
plt.show()
