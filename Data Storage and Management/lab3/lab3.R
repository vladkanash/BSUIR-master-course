require(MASS)
analyse_clust <- function(x, y, clazz) {
  k <- length(unique(clazz))
  clust <- kmeans(cbind(x, y), k)
  print(clust$totss)
  dev.new()
  plot(x, y, col=as.factor(clazz))
  dev.new()
  plot(x, y, col=as.factor(clust$cluster))
  points(clust$centers, col=1:length(clust$centers), pch=4, cex=2)
}

dat <- read.table("wine.csv", sep=",")
analyse_clust(dat$V2, dat$V11, as.factor(dat$V1))

n1 <- 100
a1 <- c(-1, 1)
r1 <- cbind(c(1, 0.1), c(0.1, 2))
n2 <- 50
a2 <- c(2, 4)
r2 <- cbind(c(2, 0.1), c(0.1, 1))
dat <- rbind(mvrnorm(n1, a1, r1), mvrnorm(n2, a2, r2))
analyse_clust(dat[,1], dat[,2], c(rep(1, n1), rep(2, n2)))2