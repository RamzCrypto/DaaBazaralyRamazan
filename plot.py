import csv
import math
import matplotlib.pyplot as plt

algorithms = ["MergeSort", "QuickSort", "QuickSelect"]
inputs = ["random", "sorted", "duplicates"]
sizes = [1000, 10000, 100000, 1000000]

data = {}
with open("results.csv") as f:
    reader = csv.DictReader(f)
    for row in reader:
        key = (row["algorithm"], row["input"], int(row["n"]))
        data[key] = {
            "time": int(row["time_ms"]),
            "comp": int(row["comparisons"]),
            "depth": int(row["max_depth"]),
        }

colors = {
    ("MergeSort", "random"): "tab:blue",
    ("MergeSort", "sorted"): "tab:blue",
    ("MergeSort", "duplicates"): "tab:blue",
    ("QuickSort", "random"): "tab:orange",
    ("QuickSort", "sorted"): "tab:orange",
    ("QuickSort", "duplicates"): "tab:orange",
    ("QuickSelect", "random"): "tab:green",
    ("QuickSelect", "sorted"): "tab:green",
    ("QuickSelect", "duplicates"): "tab:green",
}
styles = {
    "random": "-",
    "sorted": "--",
    "duplicates": ":",
}

# === Time vs n ===
plt.figure(figsize=(9, 6))
for alg in algorithms:
    for inp in inputs:
        ys = [data[(alg, inp, n)]["time"] for n in sizes]
        plt.plot(sizes, ys,
                 color=colors[(alg, inp)],
                 linestyle=styles[inp],
                 marker="o",
                 label=f"{alg}-{inp}")
plt.xscale("log")
plt.xlabel("n")
plt.ylabel("time (ms)")
plt.title("Time vs n")
plt.legend(fontsize=8)
plt.grid(True, which="both", alpha=0.3)
plt.savefig("time_vs_n.png", dpi=120)
plt.close()

# === Depth vs n ===
plt.figure(figsize=(9, 6))
for alg in algorithms:
    for inp in inputs:
        ys = [data[(alg, inp, n)]["depth"] for n in sizes]
        plt.plot(sizes, ys,
                 color=colors[(alg, inp)],
                 linestyle=styles[inp],
                 marker="o",
                 label=f"{alg}-{inp}")
plt.xscale("log")
plt.xlabel("n")
plt.ylabel("max recursion depth")
plt.title("Max recursion depth vs n")
plt.legend(fontsize=8)
plt.grid(True, which="both", alpha=0.3)
plt.savefig("depth_vs_n.png", dpi=120)
plt.close()

# === Ratio vs n ===
plt.figure(figsize=(9, 6))
for alg in algorithms:
    for inp in inputs:
        ys = []
        for n in sizes:
            c = data[(alg, inp, n)]["comp"]
            if alg == "QuickSelect":
                ys.append(c / n)
            else:
                ys.append(c / (n * math.log2(n)))
        plt.plot(sizes, ys,
                 color=colors[(alg, inp)],
                 linestyle=styles[inp],
                 marker="o",
                 label=f"{alg}-{inp}")
plt.xscale("log")
plt.xlabel("n")
plt.ylabel("ratio")
plt.title("Ratio vs n")
plt.legend(fontsize=8)
plt.grid(True, which="both", alpha=0.3)
plt.savefig("ratio_vs_n.png", dpi=120)
plt.close()

print("Done: time_vs_n.png, depth_vs_n.png, ratio_vs_n.png")